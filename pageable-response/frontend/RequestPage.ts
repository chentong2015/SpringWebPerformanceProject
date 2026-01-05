// TODO. 该类型和后端Pageable类型兼容
// org.springframework.data.domain.Pageable
export class RequestPage {

    constructor(public page?: number,
                public size?: number,
                public sort?: string[],
                public unpaged?: boolean,
                public createdDate?: string,
                public auditEventImport?: string,
                public app?: string,
                public pid?: string,
                public ptuuid?: string) {
    }
}
