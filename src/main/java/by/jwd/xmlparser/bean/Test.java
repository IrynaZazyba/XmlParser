package by.jwd.xmlparser.bean;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

public class Test implements Serializable {

    private static final long serialVersionUID = -6391741767365870392L;

    private String id;
    private String title;
    private int key;
    private LocalTime time;
    private TestGroup testGroup;
    private Set<Question> questions;
    private LocalDate deletedAt;


    public Test() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public TestGroup getTestGroup() {
        return testGroup;
    }

    public void setTestGroup(TestGroup testGroup) {
        this.testGroup = testGroup;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
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
        if (getClass() != obj.getClass())
            return false;
        Test other= (Test) obj;
        if (id== null) {
            if (other.id != null) return false;
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (title == null) {
            if (other.title != null) return false;
        } else if (!title.equals(other.title)) {
            return false;
        }
        if(key!=other.key) return false;
        if (time == null) {
            if (other.time != null) return false;
        } else if (!time.equals(other.time)) {
            return false;
        }
        if (testGroup == null) {
            if (other.testGroup != null) return false;
        } else if (!testGroup.equals(other.testGroup)) {
            return false;
        }
        if (questions == null) {
            if (other.questions != null) return false;
        } else if (!questions.equals(other.questions)) {
            return false;
        }
        if (deletedAt == null) {
            if (other.deletedAt!= null) return false;
        } else if (!deletedAt.equals(other.deletedAt)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result=result*prime+(id!=null?id.hashCode():1);
        result = result * prime + (title != null ? title.hashCode() : 1);
        result = result * prime + key;
        result = result * prime + (time != null ? time.hashCode() : 1);
        result = result * prime + (testGroup != null ? testGroup.hashCode() : 1);
        result = result * prime + (questions != null ? questions.hashCode() : 1);
        result = result * prime + (deletedAt != null ? deletedAt.hashCode() : 1);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getName() + '@' +
                "id=" + id +
                ", title='" + title +
                ", key=" + key +
                ", time=" + time +
                ", testGroup=" + testGroup +
                ", questions=" + questions +
                ", deletedAt=" + deletedAt;
    }
}
