namespace java com.zhou.thrift

# 身份认证类型
enum UserVerifyType {
    NOT_VERIFIED = 0,
    NORMAL_VERIFY = 1,
    BRAND_VERIFY = 2,
    ORG_VERIFY = 3,
    ENT_VERIFY = 4
}

# 身份认证状态
enum IdentityStatus {
    NOT_IDENTIFIED = 0,
    IDENTIFIED = 1
}

# 加V认证状态
enum VerifyStatus {
    //未认证
    NOT_VERIFIED = 0,
    //已认证
    VERIFIED = 1
    //退回状态
    RETURN = 2
    //审核中
    AUDITING = 3
}

struct GetUserVerifyInfoRequest {
    1: required string userId
    2: optional bool needExtra  # 是否获取附加信息
}

struct GetUserVerifyInfoResponse {
    1: optional TUserVerifyInfo userVerifyInfo
}


struct TUserVerifyInfo {
    1: required UserVerifyType verifyType,
    2: required IdentityStatus identityStatus,
    3: required VerifyStatus verifyStatus,
    4: optional string verifyShowContent,
    5: optional string verifyFirstClass,
    6: optional string verifySecondClass,
    7: optional string extraInfo
}

service testThriftService{
    string getDate(1:string userName),

    GetUserVerifyInfoResponse getUserVerifyInfo(
        1: required GetUserVerifyInfoRequest getUserVerifyInfoRequest
    )

}