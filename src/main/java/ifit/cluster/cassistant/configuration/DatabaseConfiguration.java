package ifit.cluster.cassistant.configuration;

import ifit.cluster.cassistant.domain.Conference;
import ifit.cluster.cassistant.domain.Question;
import ifit.cluster.cassistant.domain.Status;
import ifit.cluster.cassistant.domain.Topic;
import ifit.cluster.cassistant.repository.ConferenceRepository;
import ifit.cluster.cassistant.repository.QuestionRepository;
import ifit.cluster.cassistant.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Date;

@Configuration
public class DatabaseConfiguration {

    private final TopicRepository topicRepository;
    private final ConferenceRepository conferenceRepository;
    private final QuestionRepository questionRepository;

    @Autowired
    public DatabaseConfiguration(TopicRepository topicRepository, ConferenceRepository conferenceRepository, QuestionRepository questionRepository) {
        this.topicRepository = topicRepository;
        this.conferenceRepository = conferenceRepository;
        this.questionRepository = questionRepository;
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {

        return args -> {
            Conference conference = new Conference("Confer1", "Java");

            Topic topic1 = new Topic(conference, "topic1", "java base", "hz", new Date());
            Topic topic2 = new Topic(conference, "topic2", "java core", "hz", new Date());

            Question question1 = new Question("kurvamat@mail.ua", "What java version is better?", topic1, 0, Status.NEW);
            Question question2 = new Question("haha@mail.ua", "Where is coffee from java beans produced?", topic1, 0, Status.NEW);

            conferenceRepository.save(conference);
            topicRepository.saveAll(Arrays.asList(topic1, topic2));
            questionRepository.saveAll(Arrays.asList(question1, question2));

        };
    }
}
