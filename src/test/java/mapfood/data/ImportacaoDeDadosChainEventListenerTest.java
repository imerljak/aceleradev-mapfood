package mapfood.data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ImportacaoDeDadosChainEventListenerTest {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private ApplicationContext context;

    @MockBean
    private ImportacaoDeDadosChain chain;

    @Test
    public void deveExecutarImportacaoQuantoContextoIniciar() {

        ContextRefreshedEvent event = new ContextRefreshedEvent(context);
        publisher.publishEvent(event);


        verify(chain, atLeastOnce()).onApplicationEvent(event);
    }

}