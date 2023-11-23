package kodlama.io.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ch.qos.logback.core.model.Model;
import kodlama.io.rentACar.business.abstracs.ModelService;
import kodlama.io.rentACar.business.requests.CreateModelRequest;
import kodlama.io.rentACar.business.responses.GetAllModelResponse;
import kodlama.io.rentACar.core.utilities.mappers.ModelMapperService;
import kodlama.io.rentACar.dataAccess.abstracs.ModelRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ModelManager implements ModelService {

	private ModelRepository modelRepository;
	private ModelMapperService modelMapperService;

	@Override
	public List<GetAllModelResponse> getAll() {
		List<Model> models = modelRepository.findAll();
		
		List<GetAllModelResponse> modelsResponse = models.stream().map(model -> this.modelMapperService.forResponse().map(model, GetAllModelResponse.class)).collect(Collectors.toList());
	
		return modelsResponse;
	}

	@Override
	public void add(CreateModelRequest createModelRequest) {
		Model model = this.modelMapperService.forRequest().map(createModelRequest, Model.class);
		
		this.modelRepository.save(model);
		
	}

}
