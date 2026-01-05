// @ts-ignore
import {HttpClient, HttpResponse} from '@angular/common/http';
import {RequestPage} from './RequestPage';
import {createRequestOption} from './RequestUtil';
import {EntityModel} from "./EntityModel";

export class WebClientApp {

    constructor(private http: HttpClient) {
    }

    // TODO. Web前端自定义Page分页请求param参数, 获取返回的分页内容
    findEntitiesPageable(req?: RequestPage): HttpResponse<EntityModel[]> {
        let options = createRequestOption(req);
        return this.http.get<EntityModel[]>(`http:://localhost/test`, {params: options, observe: 'response'},
        );
    }
}