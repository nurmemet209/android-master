package com.cn.entity;

import java.util.List;

/**
 * Created by nurmemet on 2016/5/20.
 */
public class SearchPage extends BaseEntity {

    private List<String> history;
    private List<String> prompt;


    public List<String> getPrompt() {
        return prompt;
    }

    public void setPrompt(List<String> prompt) {
        this.prompt = prompt;
    }

    public List<String> getHistory() {
        return history;
    }

    public void setHistory(List<String> history) {
        this.history = history;
    }



    @Override
    public long getId() {
        return -1;
    }

    @Override
    public String getName() {
        return "";
    }
}
