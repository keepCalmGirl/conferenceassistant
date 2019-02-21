package ifit.cluster.cassistant.configuration;

import ifit.cluster.cassistant.domain.*;
import ifit.cluster.cassistant.repository.ConferenceRepository;
import ifit.cluster.cassistant.repository.QuestionRepository;
import ifit.cluster.cassistant.repository.TopicRepository;
import ifit.cluster.cassistant.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.Date;

@Configuration
public class DatabaseConfiguration {

    private final TopicRepository topicRepository;
    private final ConferenceRepository conferenceRepository;
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public DatabaseConfiguration(TopicRepository topicRepository, ConferenceRepository conferenceRepository, QuestionRepository questionRepository, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.topicRepository = topicRepository;
        this.conferenceRepository = conferenceRepository;
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {

        return args -> {
            Conference conference = new Conference("Confer1", "Java");

            Topic topic1 = new Topic(conference, "topic1", "java base", "hz", new Date(), 0);
            Topic topic2 = new Topic(conference, "topic2", "java core", "hz", new Date(), 0);

            Question question1 = new Question("kurvamat@mail.ua", "What java version is better?", topic1);
            Question question2 = new Question("haha@mail.ua", "Where is coffee from java beans produced?", topic1);


            User user1 = new User("example@email.com", "Petro", "Roshen", Role.USER, bCryptPasswordEncoder.encode("1111"));
            User user2 = new User("1@gmail.com", "Baba", "Yaha", Role.MODERATOR, bCryptPasswordEncoder.encode("yaha"));

            conferenceRepository.save(conference);
            topicRepository.saveAll(Arrays.asList(topic1, topic2));
            questionRepository.saveAll(Arrays.asList(question1, question2));
            userRepository.saveAll(Arrays.asList(user1, user2));

        };
    }
}
