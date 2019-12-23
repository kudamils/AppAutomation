package apps.data;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APIData {

    private static String authToken;
    private static String contentType = "application/json";
    private static String appVersion;
    private static String userAgentTest;
    private static String userAgent;
    private static String identity;
    private static String apiUrl;
    private static String accountsUrl;
    private static String clientdId;
    private static String clientSecretId;
    private static Object body;
    private static RequestSpecification requestSpecification;
    private static Response response;

    public static String getAuthToken() {
        return authToken;
    }

    public static void setAuthToken(String authToken) {
        APIData.authToken = authToken;
    }

    public static String getContentType() {
        return contentType;
    }

    public static void setContentType(String contentType) {
        APIData.contentType = contentType;
    }

    public static String getAppVersion() {
        return appVersion;
    }

    public static void setAppVersion(String appVersion) {
        APIData.appVersion = appVersion;
    }

    public static String getUserAgentTest() {
        return userAgentTest;
    }

    public static void setUserAgentTest(String userAgentTest) {
        APIData.userAgentTest = userAgentTest;
    }

    public static String getUserAgent() {
        return userAgent;
    }

    public static void setUserAgent(String userAgent) {
        APIData.userAgent = userAgent;
    }

    public static String getIdentity() {
        return identity;
    }

    public static void setIdentity(String identity) {
        APIData.identity = identity;
    }

    public static String getApiUrl() {
        return apiUrl;
    }

    public static void setApiUrl(String apiUrl) {
        APIData.apiUrl = apiUrl;
    }

    public static String getAccountsUrl() {
        return accountsUrl;
    }

    public static void setAccountsUrl(String accountsUrl) {
        APIData.accountsUrl = accountsUrl;
    }

    public static String getClientdId() {
        return clientdId;
    }

    public static void setClientdId(String clientdId) {
        APIData.clientdId = clientdId;
    }

    public static String getClientSecretId() {
        return clientSecretId;
    }

    public static void setClientSecretId(String clientSecretId) {
        APIData.clientSecretId = clientSecretId;
    }

    public static Object getBody() {
        return body;
    }

    public static void setBody(Object body) {
        APIData.body = body;
    }

    public static RequestSpecification getRequestSpecification() {
        return requestSpecification;
    }

    public static void setRequestSpecification(RequestSpecification requestSpecification) {
        APIData.requestSpecification = requestSpecification;
    }

    public static Response getResponse() {
        return response;
    }

    public static void setResponse(Response response) {
        APIData.response = response;
    }
}
