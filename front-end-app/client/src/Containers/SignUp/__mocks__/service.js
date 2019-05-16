export const areMatchingPasswords = jest.fn((a,b) => {
    console.log("mocked func :areMatchingPasswords");
    console.log(a===b);
    return false
});

export const formUserDetails = jest.fn((a,b) =>{
    console.log("mock func :formUserDetails");
    return "tester"
});