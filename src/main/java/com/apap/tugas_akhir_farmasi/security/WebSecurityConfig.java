package com.apap.tugas_akhir_farmasi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
			.csrf().ignoringAntMatchers("/api/**").and()
			.authorizeRequests()
			.antMatchers("/css/**").permitAll()
			.antMatchers("/api/**").permitAll()
			.antMatchers("/js/**").permitAll()
			.antMatchers("/datatable/**","/DataTables-1.10.18/**","/style.css","/bg.jpg","/a.jpg").permitAll()
			
			.antMatchers("/medical-supplies/").hasAnyAuthority("Staff Apoteker", "Admin IGD", "Admin Farmasi")
			.antMatchers("/api/daftar-medical-service").hasAnyAuthority("Admin IGD")
			.antMatchers("/medical-supplies/tambah", "/medical-supplies/ubah/**", "medical-supplies/permintaan/ubah", "/medical-supplies/jadwal-staf/**").hasAnyAuthority( "Admin Farmasi")
			.antMatchers( "/medical-supplies/{idMedicalSupllies}/","/rawat-jalan/obat/tambah", "/medical-supplies/perencanaan", "/medical-supplies/permintaan").hasAnyAuthority("Admin Farmasi","Staff Apoteker")
			.antMatchers("/medical-supplies/perencanaan/tambah").hasAnyAuthority("Staff Apoteker")
			.anyRequest().authenticated()
			.and()
			.formLogin()
			.loginPage("/login")
			.permitAll()
			.and()
			.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
			.permitAll();
			
	}
	
 
	
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	/**@Autowired
	public void configureGlobal (AuthenticationManagerBuilder auth) throws Exception{
		auth.inMemoryAuthentication()
			.passwordEncoder(encoder())
			.withUser("adminfarmasi").password(encoder().encode("admin123"))
			.roles("USER");
	}
	**/
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
	}
}