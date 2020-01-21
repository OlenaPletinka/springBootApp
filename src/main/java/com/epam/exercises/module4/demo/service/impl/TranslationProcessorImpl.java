package com.epam.exercises.module4.demo.service.impl;

import com.epam.exercises.module4.demo.service.TranslationProcessor;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Service
public class TranslationProcessorImpl implements TranslationProcessor {
  private final Map<String, Map<String, String>> dictionary = addTodictionary();

  @Override
  public String translate(String languege, String key) {
    return dictionary.get(languege).get(key);
  }

  private Map<String, Map<String, String>> addTodictionary() {
    Map<String, Map<String, String>> dictionary = new HashMap<>();
    Map<String, String> englishDictionary = prepareEngDictionary();
    dictionary.put("En", englishDictionary);
    return dictionary;
  }

  private Map<String, String> prepareEngDictionary() {
    Properties properties = new Properties();

    try {
      properties.load(new FileInputStream(Thread.currentThread().getContextClassLoader()
                .getResource("").getPath()+"messages-eng.properties"));
    } catch (Exception e) {
      e.printStackTrace();
    }
    Map<String, String> engDictionary = new HashMap<>();
    for (String key : properties.stringPropertyNames()) {
      String value = properties.getProperty(key);
      engDictionary.put(key, value);
    }
    return engDictionary;
  }
}
