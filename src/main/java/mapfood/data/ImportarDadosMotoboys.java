package mapfood.data;

import org.springframework.stereotype.Component;

@Component
public class ImportarDadosMotoboys implements ImportadorDeDados {

    @Override
    public void importar() {
        throw new UnsupportedOperationException("Not implemented, yet.");
    }

    @Override
    public void proximoPasso(ImportadorDeDados next) {
        throw new UnsupportedOperationException("Not implemented, yet");
    }
}
