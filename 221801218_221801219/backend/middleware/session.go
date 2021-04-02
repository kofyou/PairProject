package middleware

import (
	"github.com/gin-contrib/sessions"
	"github.com/gin-contrib/sessions/cookie"
	"github.com/gin-gonic/gin"
	"net/http"
)

// Session 初始化session
func Session(secret string) gin.HandlerFunc {
	store := cookie.NewStore([]byte(secret))
	//Also set Secure: true if using SSL, you should though
	store.Options(sessions.Options{MaxAge: 7 * 86400, Path: "/", Secure: false, HttpOnly: true, SameSite: http.SameSiteStrictMode})
	return sessions.Sessions("gin-session", store)
}
