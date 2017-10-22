package enty;

/**
 * enty
 * <p>
 * Created by ${kun}
 * 2017/10/23
 */

public class Answer {

    /**
     * answer : {"answerType":"openQA","emotion":"default","question":{"question":"你好","question_ws":"你好/VI//"},"text":"你也好啊。","topic":"32184199507836297","topicID":"32184199507836297","type":"T"}
     * man_intv :
     * no_nlu_result : 0
     * operation : ANSWER
     * rc : 0
     * service : openQA
     * sid : cid6f19eccd@ch00b80d480ede010001
     * status : 0
     * text : 你好
     * uuid : atn00cc7d51@ch24da0d480edf6f2601
     */

    public AnswerBean answer;
    public String man_intv;
    public int no_nlu_result;
    public String operation;
    public int rc;
    public String service;
    public String sid;
    public int status;
    public String text;
    public String uuid;

    public static class AnswerBean {
        /**
         * answerType : openQA
         * emotion : default
         * question : {"question":"你好","question_ws":"你好/VI//"}
         * text : 你也好啊。
         * topic : 32184199507836297
         * topicID : 32184199507836297
         * type : T
         */

        public String answerType;
        public String emotion;
        public QuestionBean question;
        public String text;
        public String topic;
        public String topicID;
        public String type;

        public static class QuestionBean {
            /**
             * question : 你好
             * question_ws : 你好/VI//
             */

            public String question;
            public String question_ws;
        }
    }
}
