import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class TestAutomationScript {

   import logging
logging.basicConfig(level=logging.INFO)
@app.route("/find_matches_from_jira_steps", methods=["POST"])
def find_step_level_matches():
    try:
        data = request.json
        JIRA_URL = data.get("jira_url", "").strip()
        JIRA_PROJECT_KEY = data.get("project_key", "").strip()
        JIRA_USERNAME = data.get("username", "").strip()
        JIRA_API_TOKEN = data.get("api_token", "").strip()
        INPUT_DESCRIPTION = data.get("test_case", "").strip()
 
        if not all([JIRA_URL, JIRA_PROJECT_KEY, JIRA_USERNAME, JIRA_API_TOKEN, INPUT_DESCRIPTION]):
            return jsonify({"error": "Missing one or more required fields"}), 400
 
        # Connect to JIRA
        options = {"server": JIRA_URL}
        try:
            jira = JIRA(options, basic_auth=(JIRA_USERNAME, JIRA_API_TOKEN))
        except Exception as auth_error:
            return jsonify({"error": f"Authentication failed: {auth_error}"}), 401
 
        # Fetch test cases from JIRA
        project_keys = [key.strip() for key in JIRA_PROJECT_KEY.split(",") if key.strip()]
        project_str = ",".join(f'"{key}"' for key in project_keys)
        try:
            jql = f"project in ({project_str}) AND issuetype=Test"
            issues = jira.search_issues(jql, maxResults=1000)
        except Exception as jql_error:
            return jsonify({"error": f"Failed to fetch issues with JQL: {jql_error}"}), 400
 
        # Prepare JIRA steps and metadata
        all_jira_steps = []
        jira_meta = []
 
        for issue in issues:
            desc = issue.fields.description or ""
            steps = [s.strip() for s in desc.split("\n") if s.strip()]
            for step in steps:
                all_jira_steps.append(step)
                jira_meta.append({"id": issue.key, "step": step})
 
        if not all_jira_steps:
            return jsonify({"error": "No steps found in JIRA test cases"}), 404
 
        jira_embeddings = model.encode(all_jira_steps, normalize_embeddings=True)
 
        # Prepare input steps
        input_steps = [step.strip() for step in INPUT_DESCRIPTION.split("\n") if step.strip()]
        input_embeddings = model.encode(input_steps, normalize_embeddings=True)
 
        response = []
 
        for i, step_embedding in enumerate(input_embeddings):
            step_text = input_steps[i]
            sims = cosine_similarity([step_embedding], jira_embeddings)[0]
 
            top_matches = []
            for idx, sim_score in enumerate(sims):
                if sim_score >= 0.85:
                    top_matches.append({
                        "Matched_ID": jira_meta[idx]["id"],
                        "Matched_Step": jira_meta[idx]["step"],
                        "Similarity": round(sim_score, 3)
                    })
 
            top_matches_sorted = sorted(top_matches, key=lambda x: x["Similarity"], reverse=True)[:3]
 
            result = {
                "Input_Step": step_text,
                "Top_Matches": top_matches_sorted if top_matches_sorted else "No match found above 85% similarity"
            }
 
            response.append(result)
 
        return jsonify(response)
 
    except Exception as e:
        logging.exception("Error during matching")
        return jsonify({"error": str(e)}), 500
}
