package com.hoarse.auction.web.horse;

import com.hoarse.auction.web.dto.horse.HorseDto;
import com.hoarse.auction.web.dto.horse.HorseupdateDto;
import com.hoarse.auction.web.entity.horse.Horse;
import com.hoarse.auction.web.entity.member.Member;
import com.hoarse.auction.web.repository.hoarse.HorseRepository;
import com.hoarse.auction.web.service.horse.HorseService;
import com.hoarse.auction.web.serviceImpl.hoarse.HorseServiceImpl;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class horseTest {

    @InjectMocks
    private Horse horse;

    @Mock
    private HorseRepository horseRepository;
    @Mock
    private HorseService horseService;


    @Mock
    private Member member;

    @Test
    @DisplayName("말 생성 테스트")
    public void testCreateHorse() {
        Member owner = new Member();
        Member producer = new Member();

        // Horse 객체 생성
        Horse horse = Horse.builder()
                .name("Test Horse")
                .birth("2024-01-01")
                .furcolor("Black")
                .mother(null)
                .father(null)
                .owner(owner)
                .producer(producer)
                .uniqueNum("123456")
                .bidPrice(null)
                .build();

        // Horse 객체의 속성이 올바르게 설정되었는지 검증
        assertEquals("Test Horse", horse.getName());
        assertEquals("2024-01-01", horse.getBirth());
        assertEquals("Black", horse.getFurcolor());
        assertNull(horse.getMother());
        assertNull(horse.getFather());
        assertEquals(owner, horse.getOwner());
        assertEquals(producer, horse.getProducer());
        assertEquals("123456", horse.getUniqueNum());
        assertNull(horse.getBidPrice());
    }

    @Test
    @DisplayName("말 수정 테스트")
    public void testUpdateHorse() {
        // Given
        Long hoarseId = 1L;
        HorseupdateDto requestDto = new HorseupdateDto();
        requestDto.setOwner(new Member());
        requestDto.setFather(new Horse());
        requestDto.setMother(new Horse());
        requestDto.setName("Test Horse");
        requestDto.setFurcolor("Black");

        when(horseRepository.findById(hoarseId)).thenReturn(Optional.of(horse));
        when(horse.getProducer()).thenReturn(member);
        when(member.getUsername()).thenReturn("testUser");

        // When
        horse.update(requestDto);

        // Then
        assertEquals(requestDto.getName(), horse.getName());
        assertEquals(requestDto.getFurcolor(), horse.getFurcolor());
        verify(horseRepository, times(1)).save(horse);
    }


    @Test
    public void testDeleteHorse() {

        Optional<Horse> foundHorse =horseRepository.findById(horse.getId());
        assertThat(foundHorse).isPresent();

        // Delete horse
        horseService.deleteHorse(horse.getId(),horse.getOwner());

        // After deletion
        foundHorse = horseRepository.findById(horse.getId());
        assertThat(foundHorse).isNotPresent();
    }
}

