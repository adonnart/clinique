package fr.eni.clinique.ihm.login;


import javax.swing.JOptionPane;
import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.LoginManager;
import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.ihm.MainFrameController;

public class EcranLoginController implements ILoginObserver {

	private EcranLogin ecrLogin;
	Personnel p ;
	private LoginManager mger;

	private static EcranLoginController instance;

	public static synchronized EcranLoginController get() {
		if (instance == null) {
			instance = new EcranLoginController();
		}
		return instance;
	}

	public EcranLoginController() {

		try {
			mger = LoginManager.getInstance();
		} catch (fr.eni.clinique.bll.BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void startApp() {
		ecrLogin = new EcranLogin();
		ecrLogin.setVisible(true);

	}

	@Override
	public void valider() {
		System.out.println("test valider");
		p = new Personnel();
		p.setNom(ecrLogin.getTxtNom().getText());	
		p.setMotPasse(new String(ecrLogin.getPassField().getPassword()));
		
		try {
			System.out.println(mger.checkConnexion(p));
			if (mger.checkConnexion(p) == null) {
				ecrLogin.msgErreur("Login ERROR");
			}
			if(mger.checkConnexion(p).getRole().equalsIgnoreCase("sec")){
				JOptionPane.showMessageDialog(ecrLogin,"Login Secretaire","Connexion",JOptionPane.PLAIN_MESSAGE);
				MainFrameController.get().openMainFrame();
				ecrLogin.setVisible(false);
			}
			if(mger.checkConnexion(p).getRole().equalsIgnoreCase("vet")){
				JOptionPane.showMessageDialog(ecrLogin,"Login Veterinaire","Connexion",JOptionPane.PLAIN_MESSAGE);
				MainFrameController.get().openMainFrame();
				ecrLogin.setVisible(false);
			}
			if(mger.checkConnexion(p).getRole().equalsIgnoreCase("adm")){
				JOptionPane.showMessageDialog(ecrLogin,"Login Administrateur","Connexion",JOptionPane.PLAIN_MESSAGE);
				MainFrameController.get().openMainFrame();
				ecrLogin.setVisible(false);
			}
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public Personnel getPersonnelConnected(){
		try {
			return mger.checkConnexion(p);
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
