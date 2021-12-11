package com.lelakowski.ERPMetalbud.pim.domain.repository;

import com.lelakowski.ERPMetalbud.pim.domain.model.Profession;
import com.lelakowski.ERPMetalbud.pim.testkit.ProfessionMocks;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class TestProfessionRepositorySuite {

    private final Profession profession1 = ProfessionMocks.profession1;
    private final Profession profession2 = ProfessionMocks.profession2;
    private final Profession profession3 = ProfessionMocks.profession3;
    private final Profession profession4 = ProfessionMocks.profession4;
    private final TreeMap<Long, Profession> professionData = initData();
    private final ProfessionRepository professionRepository = new TestProfessionRepository(professionData);

    @Test
    @DisplayName(".getOne() method test")
    void test1() {

        Assertions.assertEquals(professionRepository.getOne(profession1.getId()), profession1);
        Assertions.assertEquals(professionRepository.getOne(profession2.getId()), profession2);
        Assertions.assertEquals(professionRepository.getOne(profession3.getId()), profession3);

        Assertions.assertEquals(professionRepository.getOne(5L), null);
    }

    @Test
    @DisplayName(".findAll() method test")
    void test2() {
        List<Profession> expectedList = Arrays.asList(profession1, profession2, profession3, profession4);
        List<Profession> actualList = professionRepository.findAll();
        Assertions.assertEquals(actualList.size(), expectedList.size());
        Assertions.assertTrue(expectedList.containsAll(actualList));
        Assertions.assertTrue(actualList.containsAll(expectedList));
    }

    @Test
    @DisplayName(".existsById() method test")
    void test3() {
        Assertions.assertTrue(professionRepository.existsById(profession1.getId()));
        Assertions.assertTrue(professionRepository.existsById(profession2.getId()));
        Assertions.assertTrue(professionRepository.existsById(profession2.getId()));
        Assertions.assertTrue(professionRepository.existsById(profession3.getId()));
        Assertions.assertFalse(professionRepository.existsById(5L));
    }


    private TreeMap<Long, Profession> initData() {
        TreeMap<Long, Profession> data = new TreeMap<>();
        data.put(profession1.getId(), profession1);
        data.put(profession2.getId(), profession2);
        data.put(profession3.getId(), profession3);
        data.put(profession4.getId(), profession4);
        return data;
    }


}