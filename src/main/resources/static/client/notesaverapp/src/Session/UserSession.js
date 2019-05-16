let userEmail = '';
export const setUser = (email) => {
    userEmail = email;
};
export const getUser = () => {
    return userEmail;
};
export const resetUser = () => {
    userEmail = '';
};