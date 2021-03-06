package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's note in the address book.
 */
public class Note {

    public static final String MESSAGE_NOTE_CONSTRAINTS = "Note should not be blank"
        + " and have only one whitespace between it and the index.";
    public static final String MESSAGE_NOTE_EMPTY = "Note is empty!";

    /**
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String NOTE_INVALIDATION_REGEX = "[\\p{Space}](.*)";

    // Text in an unedited or reset note
    public static final String DEFAULT_NOTE = "<No note added>";

    // Edit this to change what a text checks for before deciding to add a full stop.
    private static final String END_OF_SENTENCE_REGEX = "[.!?]";

    /**
     * Checks if note ends with a character from {@code END_OF_SENTENCE_REGEX}.
     */
    private static final String NOTE_PUNCTUATION_REGEX = "(.*)" + END_OF_SENTENCE_REGEX;

    private String text;

    /**
     * Constructs a {@code Note} with default text.
     */
    public Note() {
        this.text = DEFAULT_NOTE;
    }

    /**
     * Constructs a {@code Note} with input text, punctuates text with no punctuation,
     * except if input text is {@code DEFAULT_NOTE}.
     */
    public Note(String text) {
        requireNonNull(text);
        if (text.equals(DEFAULT_NOTE)) {
            this.text = DEFAULT_NOTE;
        } else if (!isPunctuated(text)) {
            this.text = text + ".";
        } else {
            this.text = text;
        }
    }

    @Override
    public String toString() {
        return text;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Note // instanceof handles nulls
                && text.equals(((Note) other).text)); // state check
    }

    @Override
    public int hashCode() {
        return text.hashCode();
    }

    /**
     * Returns a new Note object with added text in its text.
     * Guarantees: text is not null.
     * @param text to be added
     */
    public Note add(String text) {
        assert(text != null);
        String editedText = this.text;
        // Empty text if default
        if (!this.hasChanged()) {
            editedText = "";
        } else {
            // If text is not default/empty, it should always be punctuated. Replace punctuation with comma.
            assert(isPunctuated(this.text));
            editedText = this.text.split(END_OF_SENTENCE_REGEX)[0];
            editedText += ",";
        }
        /*
         * If added text does not end with one of the characters in {@code END_OF_SENTENCE_REGEX},
         * add a full stop to end.
         */
        if (!isPunctuated(text)) {
            text += ".";
        }
        editedText += text;
        return new Note(editedText);
    }

    /**
     * Returns a Note object with text reset to {@code DEFAULT_NOTE}. Method name keeps internals hidden from users.
     */
    public Note delete() {
        return new Note(DEFAULT_NOTE);
    }

    /**
     * Returns false if this note has the default text. Method name keeps internals hidden from users.
     */
    public boolean hasChanged() {
        return !this.text.equals(DEFAULT_NOTE);
    }

    /**
     * Returns true if text ends with a punctuation that is present in {@code NOTE_PUNCTUATION_REGEX}.
     * Guarantees: text is not null.
     */
    public static boolean isPunctuated(String text) {
        assert(text != null);
        return text.matches(NOTE_PUNCTUATION_REGEX);
    }

    /**
     * Returns true if text does not begin with a space, is non-null and non-empty.
     */
    public static boolean isValid(String text) {
        requireNonNull(text);
        if (text.equals("")) {
            return false;
        }
        return !(text.matches(NOTE_INVALIDATION_REGEX));
    }
}
