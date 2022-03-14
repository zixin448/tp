package seedu.address.logic.commands;

import seedu.address.model.DisplayType;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Display list of class instead of list of persons. */
    private final DisplayType displayType;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, DisplayType displayType) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.displayType = displayType;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, DisplayType.PERSON);
    }

    /**
     * Constructs and returns a {@code CommandResult} with the specified {@code feedbackToUser},
     * and {@code isClass} and other fields set to their default value.
     *
     * @param feedbackToUser feedback given to user for the result displayed
     * @return the result of the command execution
     */
    public static CommandResult createClassCommandResult(String feedbackToUser) {
        return new CommandResult(feedbackToUser, false, false, DisplayType.CLASS);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }

    public DisplayType getDisplayType() {
        return displayType;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && showHelp == otherCommandResult.showHelp
                && exit == otherCommandResult.exit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit);
    }

}
