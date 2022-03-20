package seedu.address.model.person;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.tutorial.Tutorial;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@Code Tutorial}'s {@Code TutorialName} matches any of the keywords given.
 */
public class TutorialContainsKeywordsPredicate implements Predicate<Student> {
    private final List<String> keywords;

    public TutorialContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Student student) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(student.getTutorialName().name, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof  TutorialContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TutorialContainsKeywordsPredicate) other).keywords)); // state check
    }

}
