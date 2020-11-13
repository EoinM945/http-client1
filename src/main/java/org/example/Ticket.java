package org.example;

import java.io.Serializable;

public class Ticket  {



    private int id;
    private String subject;
    private String created_at;
    private String updated_at;
    private String raw_subject;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getRaw_subject() {
        return raw_subject;
    }

    public void setRaw_subject(String raw_subject) {
        this.raw_subject = raw_subject;
    }

    @Override
    public String toString() {
        return "ticket{" +
                "id='" + id +
                ", subject='" + subject + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", raw_subject='" + raw_subject + '\'' +
                '}';
    }


}