package seedu.address.model.assessment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.assessment.exceptions.AssessmentNonFoundException;
import seedu.address.model.assessment.exceptions.DuplicateAssessmentException;


/**
 * A list of Assessments that enforces the uniqueness between its elements and does not allow nulls.
 * An Assessment is considered unique by comparing using {@code Assessment#isSameAssessment(Assessment)}.
 * Adding and updating an Assessment uses {@code Assessment#isSameAssessment(Assessment)} for equality
 * to ensure that the assessment being added is unique in terms of identity in the UniqueAssessmentList.
 * However, the removal of an assessment uses {@code Assessment#equals(Object)} to ensure that the asessment
 * with exactly the same fields is removed.
 *
 * @see Assessment#isSameAssessment(Assessment)
 */
public class UniqueAssessmentList {
    private final ObservableList<Assessment> internalList = FXCollections.observableArrayList();
    private final ObservableList<Assessment> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent assessment as the given argument,
     * uses {@code Assessment#isSameAssessment(Assessment)}
     */
    public boolean contains(Assessment toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameAssessment);
    }

    /**
     * Adds an assessment to the list.
     * The assessment must not already exist in the list.
     */
    public void add(Assessment toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateAssessmentException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the assessment {@code target} in the list with {@code editedAssessment}.
     * {@code target} must exist in the list.
     * The name of {@code editedAssessment} must not be the same as another existing assessment in the list.
     */
    public void setAssessment(Assessment target, Assessment editedAssessment) {
        requireAllNonNull(target, editedAssessment);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new AssessmentNonFoundException();
        }

        if (!target.isSameAssessment(editedAssessment) && contains(editedAssessment)) {
            throw new DuplicateAssessmentException();
        }

        internalList.set(index, editedAssessment);
    }

    /**
     * Removes the equivalent assessment from the list.
     * The assessment must exist in the list.
     */
    public void remove(Assessment toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new AssessmentNonFoundException();
        }
    }

    /**
     * Returns the backing list as an unmodifiable list.
     */
    public ObservableList<Assessment> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public boolean equals(Object other) {
        return this == other
                || (other instanceof UniqueAssessmentList
                && internalList.equals(((UniqueAssessmentList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }
}
