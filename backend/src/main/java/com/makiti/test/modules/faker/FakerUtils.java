package com.makiti.test.modules.faker;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class FakerUtils {

  public static final FakeValuesService fakeValuesService = new FakeValuesService(
    new Locale("en-GB"),
    new RandomService()
  );
  public static final SimpleDateFormat dateFormat = new SimpleDateFormat(
    "yyyy-MM-dd",
    Locale.ENGLISH
  );
  public static final Faker faker = new Faker(new Locale("en-GB"));

  public static <E> E randomEnumValue(List<E> enumValues) {
    return enumValues.get(faker.random().nextInt(enumValues.size()));
  }
}
