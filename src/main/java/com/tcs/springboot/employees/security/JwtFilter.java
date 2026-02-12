package com.tcs.springboot.employees.security;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		try {
			String header = request.getHeader("Authorization");

			if (header != null && header.startsWith("Bearer ")) {

				String token = header.substring(7);

				if (jwtUtil.validateToken(token) && SecurityContextHolder.getContext().getAuthentication() == null) {

					String username = jwtUtil.extractUsername(token);

					UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null,
							Collections.emptyList());

					auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

					SecurityContextHolder.getContext().setAuthentication(auth);
				}
			}

		} catch (Exception ex) {
			// invalid token → clear context but continue filter chain
			SecurityContextHolder.clearContext();
		}

		chain.doFilter(request, response);
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {

		String path = request.getServletPath();

		return path.startsWith("/auth/") || path.startsWith("/docs") || path.startsWith("/api-docs")
				|| path.startsWith("/swagger-ui") || path.startsWith("/swagger-ui.html")
				|| path.startsWith("/v3/api-docs") || path.startsWith("/h2");
	}
}
