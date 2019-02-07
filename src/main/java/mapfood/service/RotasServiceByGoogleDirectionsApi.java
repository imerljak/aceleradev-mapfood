package mapfood.service;

import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.TrafficModel;
import com.google.maps.model.TravelMode;
import mapfood.model.dto.RotaEntrega;
import mapfood.model.dto.SolicitacaoEntrega;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
public class RotasServiceByGoogleDirectionsApi implements RotasService {

    private final GeoApiContext apiContext;
    private final EstabelecimentoService estabelecimentoService;
    private final ClienteService clienteService;

    public RotasServiceByGoogleDirectionsApi(GeoApiContext apiContext, EstabelecimentoService estabelecimentoService, ClienteService clienteService) {
        this.apiContext = apiContext;
        this.estabelecimentoService = estabelecimentoService;
        this.clienteService = clienteService;
    }

    @Override
    public List<RotaEntrega> getMelhorRotaPara(SolicitacaoEntrega solicitacaoEntrega) {

        // TODO: Recuperar origin de solicitacaoEntrega.estabelecimento
        LatLng origin = new LatLng(1D, -1D);

        // TODO: Recuperar waypoints de solicitacaoEntrega.clientes
        List<LatLng> waypoints = Collections.emptyList();

        DirectionsResult directionsResult = this.solicitaRotaAoServico(origin, waypoints);

        // Criar retorno da api mapfood com dados do DirectionsResult.

        return null;
    }

    private DirectionsResult solicitaRotaAoServico(LatLng origin, List<LatLng> waypoints) {
        //1. Recuperar clientes por ordem de distância (usar coordinateComparator?)

        try {
            return DirectionsApi.newRequest(apiContext)
                    .origin(origin)
                    .waypoints(waypoints.toArray(new LatLng[]{}))
                    .optimizeWaypoints(true)
                    .mode(TravelMode.DRIVING)
                    .trafficModel(TrafficModel.BEST_GUESS)
                    .await(); // await ou callback??

        } catch (ApiException | InterruptedException | IOException e) {
            // TODO: incluir exception generica unchecked de preferencia.
            e.printStackTrace();
            return null;
        }
    }
}
