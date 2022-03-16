package seedu.address.model.assessment;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import seedu.address.model.assessment.exceptions.AssessmentNotFoundException;
import seedu.address.model.assessment.exceptions.DuplicateAssessmentException;
import seedu.address.model.tutorial.TutorialName;

/**
 * Contains a list of AssessmentResults.
 * Each AssessmentResults in the list should correspond to an Assessment in the UniqueAssessmentList.
 *
 * TODO: implement remove and other setters, depending on how commands are implemented.
 * @see AssessmentResults
 * @see StudentResult
 * @see UniqueAssessmentList
 */
public class AssessmentResultsList {
    // Identity field
    private final TutorialName tutorialName;

    // Data field
    private final List<AssessmentResults> assessmentResultsList = new ArrayList<>();
    private final List<AssessmentResults> unmodifiableResultsList = Collections.unmodifiableList(assessmentResultsList);

    /**
     * Constructs an AssessmentResultsList.
     *
     * @param tutName the TutorialName of the Tutorial that the AssessmentResultsList is contained in.
     */
    public AssessmentResultsList(TutorialName tutName) {
        requireNonNull(tutName);
        tutorialName = tutName;
    }

    /**
     * Returns true if assessmentResultsList contains the AssessmentResults.
     */
    public boolean contains(AssessmentResults toCheck) {
        requireNonNull(toCheck);
        return assessmentResultsList.stream().anyMatch(toCheck::isSameAssessmentResults);
    }

    /**
     * Adds an AssessmentResults to the list.
     * The AssessmentResults must not already be in the list.
     */
    public void add(AssessmentResults toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateAssessmentException();
        }
        assessmentResultsList.add(toAdd);
    }

    /**
     * Removes the AssessmentResults with the given name from the list.
     */
    public void removeByName(AssessmentName name) {
        requireNonNull(name);
        for (int i = 0; i < assessmentResultsList.size(); i++) {
            if (assessmentResultsList.get(i).hasName(name)) {
                assessmentResultsList.remove(i);
                return;
            }
        }
        throw new AssessmentNotFoundException();
    }

    /**
     * Returns the backing list as an unmodifiable {@code List}.
     */
    public List<AssessmentResults> asUnmodifiableList() {
        return unmodifiableResultsList;
    }
}
