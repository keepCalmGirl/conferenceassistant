package ifit.cluster.cassistant;

import ifit.cluster.cassistant.domain.Conference;
import ifit.cluster.cassistant.domain.Question;
import ifit.cluster.cassistant.domain.Status;
import ifit.cluster.cassistant.domain.Topic;
import ifit.cluster.cassistant.repository.ConferenceRepository;
import ifit.cluster.cassistant.repository.QuestionRepository;
import ifit.cluster.cassistant.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
public class CassistantApplication {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private ConferenceRepository conferenceRepository;

    @Autowired
    private QuestionRepository questionRepository;

    public static void main(String[] args) {
        SpringApplication.run(CassistantApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {

        return args -> {
            Conference conference = new Conference("Confer1", "Java");

            Topic topic1 = new Topic(conference, "topic1", "java base", "hz", new Date(), 0);
            Topic topic2 = new Topic(conference, "topic2", "java core", "hz", new Date(), 0);

            Question question1 = new Question("kurvamat@mail.ua", "What java version is better?", topic1, 0, Status.NEW);
            Question question2 = new Question("haha@mail.ua", "Where is coffee from java beans produced?", topic1, 0, Status.NEW);

            conferenceRepository.save(conference);
            topicRepository.saveAll(Arrays.asList(topic1, topic2));
            questionRepository.saveAll(Arrays.asList(question1, question2));

        };
    }

}

