// Axios CDN을 사용하여 axios 로드 (브라우저 환경에서 사용)
if (typeof axios === "undefined") {
    throw new Error("Axios 라이브러리가 필요합니다. CDN을 추가하세요: https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js");
}

function createApi(baseUrl, initialConfig = {}) {
    const axiosInstance = axios.create({
        baseURL: baseUrl,
    });

    async function request(url, method, payload = null, config = {}) {
        try {
            const fullUrl = url?.startsWith("/") ? url : `${baseUrl}${url || ""}`;
            console.log(`요청: ${method.toUpperCase()} ${fullUrl}`, payload);

            const response = await axiosInstance({
                url: fullUrl,
                method,
                ...(method !== "get" && method !== "delete" ? { data: payload } : {}),
                ...initialConfig,
                ...config,
            });

            console.log(`응답: ${method.toUpperCase()} ${fullUrl}`, response.data);
            return response.data;
        } catch (e) {
            console.error(`에러: ${method.toUpperCase()} ${url || baseUrl}`, e.message);
            throw e;
        }
    }

    return {
        get: async (id, config, url = null) => request(url ? `${url}/${id}` : `${baseUrl}/${id}`, "get", null, config),
        post: async (payload, config, url = null) => request(url, "post", payload, config),
        put: async (payload, config, url = null) => request(url, "put", payload, config),
        del: async (id, config, url = null) => request(url ? `${url}/${id}` : `${baseUrl}/${id}`, "delete", null, config),
    };
}
