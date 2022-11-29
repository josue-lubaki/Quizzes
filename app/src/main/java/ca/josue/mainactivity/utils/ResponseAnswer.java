package ca.josue.mainactivity.utils;

public class ResponseAnswer {
    public String assertion;
    public String response;

    public ResponseAnswer(String assertion, String response) {
        this.assertion = assertion;
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public String getAssertion() {
        return assertion;
    }

    public void setAssertion(String assertion) {
        this.assertion = assertion;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
