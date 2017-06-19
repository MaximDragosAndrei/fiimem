package front.dto;

import java.util.Map;

public class FormOutEntity {
    AuthEntity auth;
    Map<String, String> fields;

    public FormOutEntity(AuthEntity auth, Map<String, String> fields) {
        this.auth = auth;
        this.fields = fields;
    }

    public AuthEntity getAuth() {
        return auth;
    }

    public void setAuth(AuthEntity auth) {
        this.auth = auth;
    }

    public Map<String, String> getFields() {
        return fields;
    }

    public void setFields(Map<String, String> fields) {
        this.fields = fields;
    }
}
