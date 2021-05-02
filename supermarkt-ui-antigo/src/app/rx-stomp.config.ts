import { InjectableRxStompConfig } from '@stomp/ng2-stompjs';
import SockJS from 'sockjs-client';
import { environment } from 'src/environments/environment';


export const rxStompConfig: InjectableRxStompConfig = {
  webSocketFactory: () => {
    return new SockJS(environment.baseUrl + '/socket');
  }
};
