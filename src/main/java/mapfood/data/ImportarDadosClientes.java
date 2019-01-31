package mapfood.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ImportarDadosClientes implements ImportadorDeDados {

    @Override
    public void importar() {
        throw new UnsupportedOperationException("Not implemented, yet.");
    }

    @Override
    public void proximoPasso(@Autowired @Qualifier("importarDadosMotoboys") ImportadorDeDados next) {
        next.importar();
    }

}
