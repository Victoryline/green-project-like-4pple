const getSessionUser = () => {
    return JSON.parse(sessionStorage.getItem("user"));
};

const getUsername = () => {
    return JSON.parse(sessionStorage.getItem("user")).username;
};

const getName = () => {
    return JSON.parse(sessionStorage.getItem("user")).name;
};

const getRole = () => {
    return JSON.parse(sessionStorage.getItem("user")).role;
};
