package no.fint.p360.rpc.p360Service;

import lombok.extern.slf4j.Slf4j;
import no.p360.model.AccessGroupService.GetAccessGroupsArgs;
import no.p360.model.AccessGroupService.GetAccessGroupsResponse;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AccessGroupService extends P360Service {

    public GetAccessGroupsResponse getAccessGroups(GetAccessGroupsArgs getAccessGroupsArgs) {

        log.info("GetAccessGroups query: {}", getAccessGroupsArgs);
        GetAccessGroupsResponse getAccessGroupsResponse = call("AccessGroupService/GetAccessGroups", getAccessGroupsArgs, GetAccessGroupsResponse.class);
        log.info("GetAccessGroupsResponse result: {}", getAccessGroupsResponse);
            return getAccessGroupsResponse;
    }
}