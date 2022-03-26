package seedu.address.model.attendance;

public class Comment {
    private final String commentString;

    public Comment(String comment) {
        this.commentString = comment;
    }

    public String getCommentString() {
        return commentString;
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
