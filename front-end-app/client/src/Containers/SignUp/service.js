export const areMatchingPasswords = (password, rePassword) => {
    return password === rePassword;
};

export const formUserDetails = (email, password) => {
    const userName = email.substring(0, email.lastIndexOf("@"));
    return {
        "email": email,
        "password": password,
        "role": null,
        "userName": userName
    };
};