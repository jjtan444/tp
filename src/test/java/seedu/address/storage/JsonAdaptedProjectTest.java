package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedProject.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalProjects.BANANA;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.project.Budget;
import seedu.address.model.project.Deadline;
import seedu.address.model.project.ProjectName;

public class JsonAdaptedProjectTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_BUDGET = "+651234";
    private static final String INVALID_DEADLINE = "2022-05";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BANANA.getProjectName().toString();
    private static final String VALID_BUDGET = BANANA.getBudget().toString();
    private static final String VALID_DEADLINE = BANANA.getDeadline().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BANANA.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validProjectDetails_returnsProject() throws Exception {
        JsonAdaptedProject project = new JsonAdaptedProject(BANANA);
        assertEquals(BANANA, project.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedProject project =
                new JsonAdaptedProject(INVALID_NAME, VALID_BUDGET, VALID_DEADLINE, VALID_TAGS);
        String expectedMessage = ProjectName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, project::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedProject project = new JsonAdaptedProject(null, VALID_BUDGET, VALID_DEADLINE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ProjectName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, project::toModelType);
    }

    @Test
    public void toModelType_invalidBudget_throwsIllegalValueException() {
        JsonAdaptedProject project =
                new JsonAdaptedProject(VALID_NAME, INVALID_BUDGET, VALID_DEADLINE, VALID_TAGS);
        String expectedMessage = Budget.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, project::toModelType);
    }

    @Test
    public void toModelType_nullBudget_throwsIllegalValueException() {
        JsonAdaptedProject project = new JsonAdaptedProject(VALID_NAME, null, VALID_DEADLINE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Budget.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, project::toModelType);
    }

    //    @Test
    //    public void toModelType_invalidDeadline_throwsIllegalValueException() {
    //        JsonAdaptedProject project =
    //                new JsonAdaptedProject(VALID_NAME, VALID_BUDGET, INVALID_DEADLINE, VALID_TAGS);
    //        String expectedMessage = Deadline.MESSAGE_CONSTRAINTS;
    //        assertThrows(IllegalValueException.class, expectedMessage, project::toModelType);
    //    }

    @Test
    public void toModelType_nullDeadline_throwsIllegalValueException() {
        JsonAdaptedProject project = new JsonAdaptedProject(VALID_NAME, VALID_BUDGET, null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Deadline.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, project::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedProject project =
                new JsonAdaptedProject(VALID_NAME, VALID_BUDGET, VALID_DEADLINE, invalidTags);
        assertThrows(IllegalValueException.class, project::toModelType);
    }

}
