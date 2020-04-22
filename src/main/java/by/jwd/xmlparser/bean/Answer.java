package by.jwd.xmlparser.bean;

import java.io.Serializable;
import java.time.LocalDate;

public class Answer implements Serializable {

    private static final long serialVersionUID = -9158973616469573267L;

    private String id;
    private String answerItem;
    private boolean result;
    private LocalDate deletedAt;


    public Answer(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAnswerItem() {
        return answerItem;
    }

    public void setAnswerItem(String answerItem) {
        this.answerItem = answerItem;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public LocalDate getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDate deletedAt) {
        this.deletedAt = deletedAt;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        Answer other = (Answer) obj;
        if (id== null) {
            if (other.id != null) return false;
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (answerItem == null) {
            if (other.answerItem != null) return false;
        } else if (!answerItem.equals(other.answerItem)) {
            return false;
        }
        if (result!=other.result) {
            return false;
        }
        if (deletedAt == null) {
            if (other.deletedAt != null) return false;
        } else if (!deletedAt.equals(other.deletedAt)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int prime=31;
        int result=1;
        result=result*prime+(id!=null?id.hashCode():1);
        result=result*prime+(answerItem!=null?answerItem.hashCode():1);
        result=result*prime+Boolean.valueOf(this.result).hashCode();
        result=result*prime+(deletedAt!=null?deletedAt.hashCode():1);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getName() +"@"+
                "id=" + id +
                ", answerItem='" + answerItem +
                ", result=" + result +
                ", deletedAt=" + deletedAt;
    }
}
