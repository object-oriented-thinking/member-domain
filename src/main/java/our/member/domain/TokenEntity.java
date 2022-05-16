package our.member.domain;

import java.time.LocalDateTime;

public class TokenEntity {
    private Long tokenId;
    private String token;
    private Long userId;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;

    public TokenEntity() {
    }

    public TokenEntity(Long tokenId, String token, Long userId, LocalDateTime createdAt, LocalDateTime expiresAt) {
        this.tokenId = tokenId;
        this.token = token;
        this.userId = userId;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
    }

    public Long getTokenId() {
        return tokenId;
    }

    public void setTokenId(Long tokenId) {
        this.tokenId = tokenId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }
}
