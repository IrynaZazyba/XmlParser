package by.jwd.xmlparser.bean;

import java.io.Serializable;
import java.time.LocalDate;

public class TestGroup implements Serializable {

    private static final long serialVersionUID = -8911563330809866168L;

    private int id;
    private String groupTitle;
    private LocalDate deletedAt;

    public TestGroup() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupTitle() {
        return groupTitle;
    }

    public void setGroupTitle(String groupTitle) {
        this.groupTitle = groupTitle;
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
        TestGroup other = (TestGroup) obj;
        if (id!= other.id) {
            return false;
        }
        if (groupTitle == null) {
            if (other.groupTitle != null) return false;
        } else if (!groupTitle.equals(other.groupTitle)) {
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
        int prime = 31;
        int result = 1;
        result=result*prime+id;
        result = result * prime + (groupTitle != null ? groupTitle.hashCode() : 1);
        result = result * prime + (deletedAt != null ? deletedAt.hashCode() : 1);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getName() + '@' +
                "id=" + id +
                ", groupTitle='" + groupTitle +
                ", deletedAt=" + deletedAt;
    }
}
