import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PlaceOrderTest {
model = SentenceTransformer("BAAI/bge-large-en")
   @app.route("/find_duplicates_from_jira", methods=["POST"])
def find_duplicates():
    try:
        data = request.json
        JIRA_URL = data.get("jira_url", "").strip()
        JIRA_PROJECT_KEY = data.get("project_key", "").strip()
        JIRA_USERNAME = data.get("username", "").strip()
        JIRA_API_TOKEN = data.get("api_token", "").strip()
 
        if not JIRA_PROJECT_KEY:
            return jsonify({"error": "project_key is required"}), 400
 
        project_keys = [key.strip() for key in JIRA_PROJECT_KEY.split(",") if key.strip()]
        project_str = ",".join(f'"{key}"' for key in project_keys)
 
        options = {"server": JIRA_URL}
        try:
            jira = JIRA(options, basic_auth=(JIRA_USERNAME, JIRA_API_TOKEN))
        except Exception as auth_error:
            return jsonify({"error": f"Authentication failed: {auth_error}"}), 401
 
        try:
            jql = f"project in ({project_str}) AND issuetype=Test"
            issues = jira.search_issues(jql, maxResults=1000)
        except Exception as jql_error:
            return jsonify({"error": f"Failed to fetch issues with JQL: {jql_error}"}), 400
 
        test_cases = []
        for issue in issues:
            desc = issue.fields.description or ""
            summary = issue.fields.summary or ""
            test_cases.append({
                "id": issue.key,
                "description": desc,
                "summary": summary
            })
 
        descriptions = [tc['description'] for tc in test_cases]
        embeddings = model.encode(descriptions, normalize_embeddings=True)
        similarity_matrix = cosine_similarity(embeddings)
 
        results = []
        for i, test_case in enumerate(test_cases):
            final_scores = []
            for j, other_case in enumerate(test_cases):
                if i == j:
                    continue
                sim_score = similarity_matrix[i][j]
                final_scores.append((j, sim_score))
 
            sorted_matches = sorted(final_scores, key=lambda x: x[1], reverse=True)
 
            top_matches = []
            for idx, score in sorted_matches:
                if score >= 0.85 and len(top_matches) < 3:
                    top_matches.append({
                        "Duplicate_ID": test_cases[idx]['id'],
                        "Summary": test_cases[idx]['summary']
                    })
 
            results.append({
                "Test_Case_ID": test_case['id'],
                "Test_Case_Description": test_case['description'],
                "Top_Duplicates": top_matches
            })
 
        return jsonify(results)
 
    except Exception as e:
        return jsonify({"error": str(e)}), 500
}
