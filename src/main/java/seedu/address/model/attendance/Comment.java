package seedu.address.model.attendance;

public class Comment {
    private String commentString;

    public Comment(String comment) {
        this.commentString = comment;
    }

    public void setCommentString(String commentString) {
        this.commentString = commentString;
    }

    public String getCommentString() {
        return commentString;
    }

    public static boolean isValidComment(String s) {
        return !s.isEmpty();
    }

    @Override
    public String toString() {
        return commentString;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Comment)) {
            return false;
        }

        Comment otherComment = (Comment) other;
        return otherComment.getCommentString().equals(getCommentString());
    }
}
