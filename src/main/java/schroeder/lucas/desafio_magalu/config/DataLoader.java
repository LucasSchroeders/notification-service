package schroeder.lucas.desafio_magalu.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import schroeder.lucas.desafio_magalu.entity.Channel;
import schroeder.lucas.desafio_magalu.entity.Status;
import schroeder.lucas.desafio_magalu.repository.ChannelRepository;
import schroeder.lucas.desafio_magalu.repository.StatusRepository;

import java.util.Arrays;

@Configuration
public class DataLoader implements CommandLineRunner {

    private final ChannelRepository channelRepository;
    private final StatusRepository statusRepository;

    public DataLoader(ChannelRepository channelRepository, StatusRepository statusRepository) {
        this.channelRepository = channelRepository;
        this.statusRepository = statusRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Arrays.stream(Channel.Values.values())
                .map(Channel.Values::toChannel)
                .forEach(channelRepository::save);

        Arrays.stream(Status.Values.values())
                .map(Status.Values::toStatus)
                .forEach(statusRepository::save);
    }
}
