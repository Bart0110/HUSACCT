package husacct.control.task;

import husacct.ServiceProvider;
import husacct.common.dto.ApplicationDTO;

import org.apache.log4j.Logger;

public class AnalyseTask implements Runnable{

	private Logger logger = Logger.getLogger(AnalyseTask.class);
	
	private MainController mainController;
	private ApplicationDTO applicationDTO;
	
	public AnalyseTask(ApplicationDTO applicationDTO){
		this.applicationDTO = applicationDTO;
	}
	
	@Override
	public void run() {
		// Thread.sleep added to support InterruptedException catch
		// InterruptedException is not yet implemented by analyse
		// Therefor this thread can never be interrupted.
		try {
			mainController.getStateController().setAnalysing(true);
			mainController.getStateController().setPreAnalysed(false);
			Thread.sleep(1);			
			logger.debug("Analysing application");		
			//ServiceProvider.getInstance().resetAnalyseService();
			//TODO: Wait for Analyse to change the parameter of analyseApplication from String[] to ArrayList<String>
			ServiceProvider.getInstance().getAnalyseService().analyseApplication(applicationDTO.projects.get(0).paths, applicationDTO.projects.get(0).programmingLanguage);
			logger.debug("Analysing finished");
			if(!mainController.getStateController().isAnalysing()) {
				ServiceProvider.getInstance().resetAnalyseService();
			}
			mainController.getStateController().setAnalysing(false);
			//ServiceProvider.getInstance().getDefineService().isReAnalyzed();
		} catch (InterruptedException exception){			
			logger.debug("RESETTING ANALYSE SERVICE");
			ServiceProvider.getInstance().resetAnalyseService();
			mainController.getStateController().setAnalysing(false);

		}
	}

}
