package models.validators;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import models.Task;

public class TaskValidator {
    public static List<String> validate(Task m){
        List<String> errors = new ArrayList<String>();



        String content_error = _validate_content(m.getContent());
        if(!content_error.equals("")){
            errors.add(content_error);
        }

        String deadline_error = _validate_deadline(m.getDeadline());
        if(!deadline_error.equals("")){
            errors.add(deadline_error);
        }



        return errors;
    }


    private static String _validate_content(String content){
        if(content == null || content.equals("")){
            return "メッセージを入力してください";
        }
        return "";
    }

    private static String _validate_deadline(Timestamp deadline){
        if(deadline == null || deadline.equals("")){
            return "締め切りを入力してください";
        }
        return "";
    }
}
