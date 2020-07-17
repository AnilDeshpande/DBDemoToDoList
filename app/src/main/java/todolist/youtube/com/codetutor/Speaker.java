package todolist.youtube.com.codetutor;

import todolist.youtube.com.codetutor.bean.ToDo;

/**
 * Any View which needs to do the speaking part
 * needs to implement this interface. In this case MainActivity
 * is implementing this interface and override this method
 */
public interface Speaker {
    void speak(ToDo toDo);
}
