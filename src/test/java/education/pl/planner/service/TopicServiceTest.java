//package education.pl.planner.service;
//
//import education.pl.planner.domain.Topic;
//import education.pl.planner.domain.TopicRepository;
//import education.pl.planner.domain.User;
//import education.pl.planner.exception.NotFoundException;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.function.Executable;
//import org.mockito.Mockito;
//
//import java.util.Collections;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class TopicServiceTest {
//
//    private TopicRepository topicRepository = mock(TopicRepository.class);
//    private TopicService topicService = new TopicService(topicRepository);
//
//    @Test
//    public void shouldGetTopicById() {
//        // given
//        User user = new User("user", "pass", "USER_ROLE");
//        Topic expected = new Topic(1, "topic title", Collections.emptySet(),
//                5, false, null, user);
//        // when
//        when(topicRepository.findById(1)).thenReturn(Optional.of(expected));
//        Topic actual = topicService.getTopicById(1);
//        // then
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void shouldThrowExceptionWhenTopicNotFoundById() {
//        // when
//        when(topicRepository.findById(12)).thenReturn(Optional.empty());
//        Executable ex = () -> topicService.getTopicById(12);
//        // then
//        assertThrows(NotFoundException.class, ex);
//    }
//
//    @Test
//    public void shouldAddNewTopic() {
//        // given
//        Topic topic = new Topic(1, "topic title", Collections.emptySet(), 5, false, null);
//        // when
//        topicService.add(topic);
//        // then
//        verify(topicRepository).save(topic);
//    }
//
//    @Test
//    public void shouldUpdateTopic() {
//        // given
//        Topic beforeUpdate = new Topic(1, "old title", Collections.emptySet(), 5, false, null);
//        Topic afterUpdate = new Topic(1, "new title", Collections.emptySet(), 10, false, null);
//        // when
//        when(topicRepository.findById(1)).thenReturn(Optional.of(beforeUpdate));
//        Topic actual = topicService.update(afterUpdate);
//        // then
//        assertEquals(afterUpdate, actual);
//    }
//
//    @Test
//    public void shouldThrowExceptionWhenIdDoesNotMatch() {
//        // when
//        when(topicRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
//        Executable executable = () -> topicService.update(new Topic());
//        // then
//        assertThrows(NotFoundException.class, executable);
//    }
//
//}
