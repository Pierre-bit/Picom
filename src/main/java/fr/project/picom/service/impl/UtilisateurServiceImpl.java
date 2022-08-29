package fr.project.picom.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.project.picom.dao.UtilisateurDao;
import fr.project.picom.model.Administrateur;
import fr.project.picom.model.Utilisateur;
import fr.project.picom.service.UtilisateurService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UtilisateurServiceImpl implements UtilisateurService, UserDetailsService{
	
	private UtilisateurDao utilisateurDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		if (username.trim().isEmpty()) {
			throw new UsernameNotFoundException("username is empty");
		}

		Utilisateur utilisateur = utilisateurDao.findByEmail(username);
		if (utilisateur == null) {
			throw new UsernameNotFoundException("user " + username + " not found");
		}
		List<GrantedAuthority> grantedAuthorities = getGrantedAuthorities(utilisateur);
		User user = new User(utilisateur.getEmail(), utilisateur.getMotDePasse(), grantedAuthorities);
		System.out.println(user);
		return user;
	}

    private List<GrantedAuthority> getGrantedAuthorities(Utilisateur utilisateur) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        if(utilisateur instanceof Administrateur) {
        	authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));	
        } else {
        	authorities.add(new SimpleGrantedAuthority("ROLE_USER"));	
        }
        return authorities;
    }

	@Override
	public Utilisateur recupUser(Long id) {
		return utilisateurDao.findById(id).orElse(null);
	}

	
}
