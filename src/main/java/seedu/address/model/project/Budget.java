package seedu.address.model.project;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represent a project budget in HR PRO Max++.
 */
public class Budget {
    public static final String MESSAGE_CONSTRAINTS =
            "Budget should only contain numbers, and it should not be blank";

    public static final String VALIDATION_REGEX = "\\d+";

    public final String value;

    /**
     * Constructs an {@code Budget}.
     *
     * @param budget A valid budget.
     */
    public Budget(String budget) {
        requireNonNull(budget);
        checkArgument(isValidBudget(budget), MESSAGE_CONSTRAINTS);
        value = budget;
    }

    /**
     * Returns true if a given string is a valid budget.
     */
    public static boolean isValidBudget(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Budget // instanceof handles nulls
                && value.equals(((Budget) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}