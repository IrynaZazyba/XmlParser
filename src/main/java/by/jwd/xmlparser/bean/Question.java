package by.jwd.xmlparser.bean;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

public class Question implements Serializable {

    private static final long serialVersionUID = -4393774148342509012L;

    private String id;
    private String questionItem;
    private Set<Answer> answers;
    private LocalDate deletedAt;

    public Question(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestionItem() {
        return questionItem;
    }

    public void setQuestionItem(String questionItem) {
        this.questionItem = questionItem;
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
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
        if (obj== null)return false;
        if(getClass() != obj.getClass()) return false;
        Question other = (Question) obj;
        if (id== null) {
            if (other.id != null) return false;
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (questionItem == null) {
            if (other.questionItem != null) return false;
        } else if (!questionItem.equals(other.questionItem)) {
            return false;
        }
        if (answers == null) {
            if (other.answers != null) return false;
        } else if (!answers.equals(other.answers)) {
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
        result=result*prime+(questionItem!=null?questionItem.hashCode():1);
        result=result*prime+(answers!=null?answers.hashCode():1);
        result=result*prime+(deletedAt!=null?deletedAt.hashCode():1);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getName() +"@"+
                "id=" + id +
                ", questionItem='" + questionItem +
                ", answers=" + answers +
                ", deletedAt=" + deletedAt;
    }
}
