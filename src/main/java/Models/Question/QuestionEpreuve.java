package Models.Question;

import java.io.Serializable;

public abstract class QuestionEpreuve extends Question implements Serializable {

 abstract public String getQuestion();
 abstract public void setQuestion(String question);
}
