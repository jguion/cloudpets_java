package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.vending.expansion.downloader.Constants;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

@zziy
public final class zzdi {
    public static final zzde<String> zzban = zzde.zza(0, "gads:sdk_core_experiment_id");
    public static final zzde<String> zzbao = zzde.zza(0, "gads:sdk_core_location", "https://googleads.g.doubleclick.net/mads/static/mad/sdk/native/sdk-core-v40.html");
    public static final zzde<Boolean> zzbap = zzde.zza(0, "gads:request_builder:singleton_webview", Boolean.valueOf(false));
    public static final zzde<String> zzbaq = zzde.zza(0, "gads:request_builder:singleton_webview_experiment_id");
    public static final zzde<Boolean> zzbar = zzde.zza(0, "gads:sdk_use_dynamic_module", Boolean.valueOf(true));
    public static final zzde<String> zzbas = zzde.zza(0, "gads:sdk_use_dynamic_module_experiment_id");
    public static final zzde<Boolean> zzbat = zzde.zza(0, "gads:sdk_crash_report_enabled", Boolean.valueOf(false));
    public static final zzde<Boolean> zzbau = zzde.zza(0, "gads:sdk_crash_report_full_stacktrace", Boolean.valueOf(false));
    public static final zzde<String> zzbav = zzde.zza(0, "gads:sdk_crash_report_class_prefix", "com.google.");
    public static final zzde<Boolean> zzbaw = zzde.zza(0, "gads:block_autoclicks", Boolean.valueOf(false));
    public static final zzde<String> zzbax = zzde.zza(0, "gads:block_autoclicks_experiment_id");
    public static final zzde<String> zzbay = zzde.zzb(0, "gads:prefetch:experiment_id");
    public static final zzde<String> zzbaz = zzde.zza(0, "gads:spam_app_context:experiment_id");
    public static final zzde<Boolean> zzbba = zzde.zza(1, "gads:spam_app_context:enabled", Boolean.valueOf(false));
    public static final zzde<Integer> zzbbb = zzde.zza(1, "gads:http_url_connection_factory:timeout_millis", 10000);
    public static final zzde<String> zzbbc = zzde.zza(1, "gads:video_exo_player:version", "1");
    public static final zzde<String> zzbbd = zzde.zza(0, "gads:video_stream_cache:experiment_id");
    public static final zzde<Integer> zzbbe = zzde.zza(1, "gads:video_stream_cache:limit_count", 5);
    public static final zzde<Integer> zzbbf = zzde.zza(1, "gads:video_stream_cache:limit_space", 8388608);
    public static final zzde<Integer> zzbbg = zzde.zza(1, "gads:video_stream_exo_allocator:segment_size", 65536);
    public static final zzde<Integer> zzbbh = zzde.zza(1, "gads:video_stream_exo_cache:buffer_size", 8388608);
    public static final zzde<Long> zzbbi = zzde.zza(1, "gads:video_stream_cache:limit_time_sec", 300);
    public static final zzde<Long> zzbbj = zzde.zza(1, "gads:video_stream_cache:notify_interval_millis", 1000);
    public static final zzde<Integer> zzbbk = zzde.zza(1, "gads:video_stream_cache:connect_timeout_millis", 10000);
    public static final zzde<Boolean> zzbbl = zzde.zza(0, "gads:video:metric_reporting_enabled", Boolean.valueOf(false));
    public static final zzde<String> zzbbm = zzde.zza(1, "gads:video:metric_frame_hash_times", "");
    public static final zzde<Long> zzbbn = zzde.zza(1, "gads:video:metric_frame_hash_time_leniency", 500);
    public static final zzde<Boolean> zzbbo = zzde.zza(1, "gads:video:force_watermark", Boolean.valueOf(false));
    public static final zzde<Boolean> zzbbp = zzde.zza(1, "gads:memory_bundle:debug_info", Boolean.valueOf(false));
    public static final zzde<Boolean> zzbbq = zzde.zza(1, "gads:memory_bundle:runtime_info", Boolean.valueOf(true));
    public static final zzde<String> zzbbr = zzde.zzb(0, "gads:spam_ad_id_decorator:experiment_id");
    public static final zzde<Boolean> zzbbs = zzde.zza(0, "gads:spam_ad_id_decorator:enabled", Boolean.valueOf(false));
    public static final zzde<String> zzbbt = zzde.zzb(0, "gads:looper_for_gms_client:experiment_id");
    public static final zzde<Boolean> zzbbu = zzde.zza(0, "gads:looper_for_gms_client:enabled", Boolean.valueOf(true));
    public static final zzde<Boolean> zzbbv = zzde.zza(0, "gads:sw_ad_request_service:enabled", Boolean.valueOf(true));
    public static final zzde<Boolean> zzbbw = zzde.zza(0, "gads:sw_dynamite:enabled", Boolean.valueOf(true));
    public static final zzde<String> zzbbx = zzde.zza(1, "gad:mraid:url_banner", "https://googleads.g.doubleclick.net/mads/static/mad/sdk/native/mraid/v2/mraid_app_banner.js");
    public static final zzde<String> zzbby = zzde.zza(1, "gad:mraid:url_expanded_banner", "https://googleads.g.doubleclick.net/mads/static/mad/sdk/native/mraid/v2/mraid_app_expanded_banner.js");
    public static final zzde<String> zzbbz = zzde.zza(1, "gad:mraid:url_interstitial", "https://googleads.g.doubleclick.net/mads/static/mad/sdk/native/mraid/v2/mraid_app_interstitial.js");
    public static final zzde<Boolean> zzbca = zzde.zza(0, "gads:enabled_sdk_csi", Boolean.valueOf(false));
    public static final zzde<String> zzbcb = zzde.zza(0, "gads:sdk_csi_server", "https://csi.gstatic.com/csi");
    public static final zzde<Boolean> zzbcc = zzde.zza(0, "gads:sdk_csi_write_to_file", Boolean.valueOf(false));
    public static final zzde<Boolean> zzbcd = zzde.zza(0, "gads:enable_content_fetching", Boolean.valueOf(true));
    public static final zzde<Integer> zzbce = zzde.zza(0, "gads:content_length_weight", 1);
    public static final zzde<Integer> zzbcf = zzde.zza(0, "gads:content_age_weight", 1);
    public static final zzde<Integer> zzbcg = zzde.zza(0, "gads:min_content_len", 11);
    public static final zzde<Integer> zzbch = zzde.zza(0, "gads:fingerprint_number", 10);
    public static final zzde<Integer> zzbci = zzde.zza(0, "gads:sleep_sec", 10);
    public static final zzde<Boolean> zzbcj = zzde.zza(0, "gad:app_index_enabled", Boolean.valueOf(true));
    public static final zzde<Boolean> zzbck = zzde.zza(0, "gads:app_index:without_content_info_present:enabled", Boolean.valueOf(true));
    public static final zzde<Long> zzbcl = zzde.zza(0, "gads:app_index:timeout_ms", 1000);
    public static final zzde<String> zzbcm = zzde.zza(0, "gads:app_index:experiment_id");
    public static final zzde<String> zzbcn = zzde.zza(0, "gads:kitkat_interstitial_workaround:experiment_id");
    public static final zzde<Boolean> zzbco = zzde.zza(0, "gads:kitkat_interstitial_workaround:enabled", Boolean.valueOf(true));
    public static final zzde<Boolean> zzbcp = zzde.zza(0, "gads:interstitial_follow_url", Boolean.valueOf(true));
    public static final zzde<Boolean> zzbcq = zzde.zza(0, "gads:interstitial_follow_url:register_click", Boolean.valueOf(true));
    public static final zzde<String> zzbcr = zzde.zza(0, "gads:interstitial_follow_url:experiment_id");
    public static final zzde<Boolean> zzbcs = zzde.zza(0, "gads:analytics_enabled", Boolean.valueOf(true));
    public static final zzde<Boolean> zzbct = zzde.zza(0, "gads:ad_key_enabled", Boolean.valueOf(false));
    public static final zzde<Integer> zzbcu = zzde.zza(0, "gads:webview_cache_version", 0);
    public static final zzde<Boolean> zzbcv = zzde.zza(1, "gads:webview_recycle:enabled", Boolean.valueOf(false));
    public static final zzde<String> zzbcw = zzde.zza(1, "gads:webview_recycle:experiment_id");
    public static final zzde<Boolean> zzbcx = zzde.zza(1, "gads:webview:ignore_over_scroll", Boolean.valueOf(true));
    public static final zzde<String> zzbcy = zzde.zzb(0, "gads:pan:experiment_id");
    public static final zzde<String> zzbcz = zzde.zza(1, "gads:rewarded:experiment_id");
    public static final zzde<Long> zzbda = zzde.zza(1, "gads:rewarded:adapter_timeout_ms", 20000);
    public static final zzde<Boolean> zzbdb = zzde.zza(1, "gads:app_activity_tracker:enabled", Boolean.valueOf(true));
    public static final zzde<Long> zzbdc = zzde.zza(1, "gads:app_activity_tracker:notify_background_listeners_delay_ms", 500);
    public static final zzde<Long> zzbdd = zzde.zza(1, "gads:app_activity_tracker:app_session_timeout_ms", TimeUnit.MINUTES.toMillis(5));
    public static final zzde<Boolean> zzbde = zzde.zza(0, "gads:ad_manager_creator:enabled", Boolean.valueOf(true));
    public static final zzde<String> zzbdf = zzde.zza(1, "gads:ad_manager_enforce_arp_invariant:experiment_id");
    public static final zzde<Boolean> zzbdg = zzde.zza(1, "gads:ad_manager_enforce_arp_invariant:enabled", Boolean.valueOf(false));
    public static final zzde<Long> zzbdh = zzde.zza(1, "gads:ad_overlay:delay_page_close_timeout_ms", (long) Constants.ACTIVE_THREAD_WATCHDOG);
    public static final zzde<Boolean> zzbdi = zzde.zza(1, "gads:interstitial_ad_pool:enabled", Boolean.valueOf(false));
    public static final zzde<Boolean> zzbdj = zzde.zza(1, "gads:interstitial_ad_pool:enabled_for_rewarded", Boolean.valueOf(false));
    public static final zzde<String> zzbdk = zzde.zza(1, "gads:interstitial_ad_pool:schema", "customTargeting");
    public static final zzde<String> zzbdl = zzde.zza(1, "gads:interstitial_ad_pool:request_exclusions", "com.google.ads.mediation.admob.AdMobAdapter/_ad");
    public static final zzde<Integer> zzbdm = zzde.zza(1, "gads:interstitial_ad_pool:max_pools", 3);
    public static final zzde<Integer> zzbdn = zzde.zza(1, "gads:interstitial_ad_pool:max_pool_depth", 2);
    public static final zzde<Integer> zzbdo = zzde.zza(1, "gads:interstitial_ad_pool:time_limit_sec", 1200);
    public static final zzde<String> zzbdp = zzde.zza(1, "gads:interstitial_ad_pool:ad_unit_exclusions", "(?!)");
    public static final zzde<String> zzbdq = zzde.zza(1, "gads:spherical_video:vertex_shader", "");
    public static final zzde<String> zzbdr = zzde.zza(1, "gads:spherical_video:fragment_shader", "");
    public static final zzde<String> zzbds = zzde.zza(1, "gads:spherical_video:experiment_id");
    public static final zzde<Boolean> zzbdt = zzde.zza(0, "gads:log:verbose_enabled", Boolean.valueOf(false));
    public static final zzde<Boolean> zzbdu = zzde.zza(1, "gads:include_local_global_rectangles", Boolean.valueOf(false));
    public static final zzde<String> zzbdv = zzde.zza(1, "gads:include_local_global_rectangles:experiment_id");
    public static final zzde<Boolean> zzbdw = zzde.zza(0, "gads:device_info_caching:enabled", Boolean.valueOf(true));
    public static final zzde<Long> zzbdx = zzde.zza(0, "gads:device_info_caching_expiry_ms:expiry", 300000);
    public static final zzde<Boolean> zzbdy = zzde.zza(0, "gads:gen204_signals:enabled", Boolean.valueOf(false));
    public static final zzde<Boolean> zzbdz = zzde.zza(0, "gads:webview:error_reporting_enabled", Boolean.valueOf(false));
    public static final zzde<Boolean> zzbea = zzde.zza(0, "gads:adid_reporting:enabled", Boolean.valueOf(false));
    public static final zzde<Boolean> zzbeb = zzde.zza(0, "gads:ad_settings_page_reporting:enabled", Boolean.valueOf(false));
    public static final zzde<Boolean> zzbec = zzde.zza(0, "gads:adid_info_gmscore_upgrade_reporting:enabled", Boolean.valueOf(false));
    public static final zzde<Boolean> zzbed = zzde.zza(0, "gads:request_pkg:enabled", Boolean.valueOf(true));
    public static final zzde<Boolean> zzbee = zzde.zza(1, "gads:gmsg:disable_back_button:enabled", Boolean.valueOf(true));
    public static final zzde<Boolean> zzbef = zzde.zza(0, "gads:gmsg:video_meta:enabled", Boolean.valueOf(true));
    public static final zzde<String> zzbeg = zzde.zza(0, "gads:gmsg:video_meta:experiment_id");
    public static final zzde<Long> zzbeh = zzde.zza(0, "gads:network:cache_prediction_duration_s", 300);
    public static final zzde<Boolean> zzbei = zzde.zza(0, "gads:mediation:dynamite_first:admobadapter", Boolean.valueOf(true));
    public static final zzde<Boolean> zzbej = zzde.zza(0, "gads:mediation:dynamite_first:adurladapter", Boolean.valueOf(true));
    public static final zzde<Long> zzbek = zzde.zza(0, "gads:ad_loader:timeout_ms", (long) Constants.WATCHDOG_WAKE_TIMER);
    public static final zzde<Long> zzbel = zzde.zza(0, "gads:rendering:timeout_ms", (long) Constants.WATCHDOG_WAKE_TIMER);
    public static final zzde<Boolean> zzbem = zzde.zza(0, "gads:adshield:enable_adshield_instrumentation", Boolean.valueOf(false));
    public static final zzde<Long> zzben = zzde.zza(1, "gads:gestures:task_timeout", 2000);
    public static final zzde<String> zzbeo = zzde.zza(1, "gads:gestures:encrypt_size_limit:experiment_id");
    public static final zzde<Boolean> zzbep = zzde.zza(1, "gads:gestures:encrypt_size_limit:enabled", Boolean.valueOf(false));
    public static final zzde<String> zzbeq = zzde.zza(1, "gads:gestures:appid:experiment_id");
    public static final zzde<Boolean> zzber = zzde.zza(1, "gads:gestures:appid:enabled", Boolean.valueOf(false));
    public static final zzde<Boolean> zzbes = zzde.zza(0, "gads:gestures:v6:enabled", Boolean.valueOf(false));
    public static final zzde<String> zzbet = zzde.zza(1, "gads:gestures:usb_query:experiment_id");
    public static final zzde<Boolean> zzbeu = zzde.zza(1, "gads:gestures:usb_query:enabled", Boolean.valueOf(false));
    public static final zzde<String> zzbev = zzde.zza(1, "gads:gestures:usb_click:experiment_id");
    public static final zzde<Boolean> zzbew = zzde.zza(1, "gads:gestures:usb_click:enabled", Boolean.valueOf(false));
    public static final zzde<Boolean> zzbex = zzde.zza(1, "gads:gestures:touchinfo:enabled", Boolean.valueOf(false));
    public static final zzde<String> zzbey = zzde.zza(1, "gads:gestures:touchinfo:experiment_id");
    public static final zzde<Boolean> zzbez = zzde.zza(1, "gads:gestures:clock_query:enabled", Boolean.valueOf(false));
    public static final zzde<String> zzbfa = zzde.zza(1, "gads:gestures:clock_query:experiment_id");
    public static final zzde<Boolean> zzbfb = zzde.zza(1, "gads:gestures:clock_click:enabled", Boolean.valueOf(false));
    public static final zzde<String> zzbfc = zzde.zza(1, "gads:gestures:clock_click:experiment_id");
    public static zzde<Boolean> zzbfd = zzde.zza(1, "gads:gestures:btl_click:enabled", Boolean.valueOf(false));
    public static zzde<String> zzbfe = zzde.zza(1, "gads:gestures:btl_click:experiment_id");
    public static final zzde<String> zzbff = zzde.zza(1, "gads:gestures:click_stk:experiment_id");
    public static final zzde<Boolean> zzbfg = zzde.zza(1, "gads:gestures:click_stk:enabled", Boolean.valueOf(true));
    public static final zzde<Boolean> zzbfh = zzde.zza(0, "gass:client:enabled", Boolean.valueOf(true));
    public static final zzde<String> zzbfi = zzde.zza(0, "gass:client:experiment_id");
    public static final zzde<Boolean> zzbfj = zzde.zza(0, "gass:enabled", Boolean.valueOf(true));
    public static final zzde<Boolean> zzbfk = zzde.zza(0, "gass:enable_int_signal", Boolean.valueOf(true));
    public static final zzde<Boolean> zzbfl = zzde.zza(0, "gads:adid_notification:first_party_check:enabled", Boolean.valueOf(true));
    public static final zzde<Boolean> zzbfm = zzde.zza(0, "gads:edu_device_helper:enabled", Boolean.valueOf(true));
    public static final zzde<Boolean> zzbfn = zzde.zza(0, "gads:support_screen_shot", Boolean.valueOf(true));
    public static final zzde<Boolean> zzbfo = zzde.zza(0, "gads:use_get_drawing_cache_for_screenshot:enabled", Boolean.valueOf(true));
    public static final zzde<String> zzbfp = zzde.zza(0, "gads:use_get_drawing_cache_for_screenshot:experiment_id");
    public static final zzde<String> zzbfq = zzde.zza(1, "gads:sdk_core_constants:experiment_id");
    public static final zzde<String> zzbfr = zzde.zza(1, "gads:sdk_core_constants:caps", "");
    public static final zzde<Long> zzbfs = zzde.zza(0, "gads:js_flags:update_interval", TimeUnit.HOURS.toMillis(12));
    public static final zzde<Boolean> zzbft = zzde.zza(0, "gads:js_flags:mf", Boolean.valueOf(false));
    public static final zzde<Boolean> zzbfu = zzde.zza(0, "gads:custom_render:ping_on_ad_rendered", Boolean.valueOf(false));
    public static final zzde<String> zzbfv = zzde.zza(0, "gads:native:engine_url", "//googleads.g.doubleclick.net/mads/static/mad/sdk/native/native_ads.html");
    public static final zzde<String> zzbfw = zzde.zza(1, "gads:native:video_url", "//googleads.g.doubleclick.net/mads/static/mad/sdk/native/native_video_ads.html");
    public static final zzde<Boolean> zzbfx = zzde.zza(1, "gads:singleton_webview_native", Boolean.valueOf(false));
    public static final zzde<String> zzbfy = zzde.zza(1, "gads:singleton_webview_native:experiment_id");
    public static final zzde<Boolean> zzbfz = zzde.zza(1, "gads:enable_untrack_view_native", Boolean.valueOf(true));
    public static final zzde<Boolean> zzbga = zzde.zza(1, "gads:reset_listeners_preparead_native", Boolean.valueOf(true));
    public static final zzde<Integer> zzbgb = zzde.zza(1, "gads:native_video_load_timeout", 10);
    public static final zzde<String> zzbgc = zzde.zza(1, "gads:native_video_load_timeout:experiment_id");
    public static final zzde<String> zzbgd = zzde.zza(1, "gads:ad_choices_content_description", "Ad Choices Icon");
    public static final zzde<Boolean> zzbge = zzde.zza(1, "gads:clamp_native_video_player_dimensions", Boolean.valueOf(true));
    public static final zzde<Boolean> zzbgf = zzde.zza(1, "gads:fluid_ad:use_wrap_content_height", Boolean.valueOf(false));
    public static final zzde<Boolean> zzbgg = zzde.zza(0, "gads:method_tracing:enabled", Boolean.valueOf(false));
    public static final zzde<Long> zzbgh = zzde.zza(0, "gads:method_tracing:duration_ms", 30000);
    public static final zzde<Integer> zzbgi = zzde.zza(0, "gads:method_tracing:count", 5);
    public static final zzde<Integer> zzbgj = zzde.zza(0, "gads:method_tracing:filesize", 134217728);
    public static final zzde<Boolean> zzbgk = zzde.zza(1, "gads:auto_location_for_coarse_permission", Boolean.valueOf(false));
    public static final zzde<String> zzbgl = zzde.zzb(1, "gads:auto_location_for_coarse_permission:experiment_id");
    public static final zzde<Long> zzbgm = zzde.zza(1, "gads:auto_location_timeout", 2000);
    public static final zzde<String> zzbgn = zzde.zzb(1, "gads:auto_location_timeout:experiment_id");
    public static final zzde<Long> zzbgo = zzde.zza(1, "gads:auto_location_interval", -1);
    public static final zzde<String> zzbgp = zzde.zzb(1, "gads:auto_location_interval:experiment_id");
    public static final zzde<Boolean> zzbgq = zzde.zza(1, "gads:fetch_app_settings_using_cld:enabled", Boolean.valueOf(false));
    public static final zzde<String> zzbgr = zzde.zza(1, "gads:fetch_app_settings_using_cld:enabled:experiment_id");
    public static final zzde<Long> zzbgs = zzde.zza(1, "gads:fetch_app_settings_using_cld:refresh_interval_ms", 7200000);
    public static final zzde<String> zzbgt = zzde.zza(1, "gads:fetch_app_settings_using_cld:refresh_interval_ms:experiment_id");
    public static final zzde<String> zzbgu = zzde.zza(0, "gads:afs:csa:experiment_id");
    public static final zzde<String> zzbgv = zzde.zza(0, "gads:afs:csa_webview_gmsg_ad_failed", "gmsg://noAdLoaded");
    public static final zzde<String> zzbgw = zzde.zza(0, "gads:afs:csa_webview_gmsg_script_load_failed", "gmsg://scriptLoadFailed");
    public static final zzde<String> zzbgx = zzde.zza(0, "gads:afs:csa_webview_gmsg_ad_loaded", "gmsg://adResized");
    public static final zzde<String> zzbgy = zzde.zza(0, "gads:afs:csa_webview_static_file_path", "/afs/ads/i/webview.html");
    public static final zzde<String> zzbgz = zzde.zza(0, "gads:afs:csa_webview_custom_domain_param_key", "csa_customDomain");
    public static final zzde<Long> zzbha = zzde.zza(0, "gads:afs:csa_webview_adshield_timeout_ms", 1000);
    public static final zzde<Boolean> zzbhb = zzde.zza(0, "gads:afs:csa_ad_manager_enabled", Boolean.valueOf(true));
    public static final zzde<Boolean> zzbhc = zzde.zza(1, "gads:parental_controls:send_from_client", Boolean.valueOf(true));
    public static final zzde<Long> zzbhd = zzde.zza(1, "gads:parental_controls:timeout", 2000);
    public static final zzde<String> zzbhe = zzde.zza(0, "gads:safe_browsing:api_key", "AIzaSyDRKQ9d6kfsoZT2lUnZcZnBYvH69HExNPE");
    public static final zzde<Long> zzbhf = zzde.zza(0, "gads:safe_browsing:safety_net:delay_ms", 2000);
    public static final zzde<Boolean> zzbhg = zzde.zza(0, "gads:safe_browsing:debug", Boolean.valueOf(false));
    public static final zzde<Boolean> zzbhh = zzde.zza(0, "gads:webview_cookie:enabled", Boolean.valueOf(true));
    public static final zzde<Integer> zzbhi = zzde.zza(1, "gads:cache:max_concurrent_downloads", 10);
    public static final zzde<Long> zzbhj = zzde.zza(1, "gads:cache:javascript_timeout_millis", (long) Constants.ACTIVE_THREAD_WATCHDOG);
    public static final zzde<Boolean> zzbhk = zzde.zza(1, "gads:cache:bind_on_foreground", Boolean.valueOf(false));
    public static final zzde<Boolean> zzbhl = zzde.zza(1, "gads:cache:bind_on_init", Boolean.valueOf(false));
    public static final zzde<Boolean> zzbhm = zzde.zza(1, "gads:cache:bind_on_request", Boolean.valueOf(false));
    public static final zzde<Long> zzbhn = zzde.zza(1, "gads:cache:bind_on_request_keep_alive", TimeUnit.SECONDS.toMillis(30));
    public static final zzde<Boolean> zzbho = zzde.zza(1, "gads:cache:use_cache_data_source", Boolean.valueOf(false));
    public static final zzde<Boolean> zzbhp = zzde.zza(1, "gads:chrome_custom_tabs:enabled", Boolean.valueOf(true));
    public static final zzde<Boolean> zzbhq = zzde.zza(1, "gads:drx_debug:enabled", Boolean.valueOf(false));
    public static final zzde<String> zzbhr = zzde.zza(1, "gads:drx_debug:debug_device_linking_url", "https://www.google.com/dfp/linkDevice");
    public static final zzde<String> zzbhs = zzde.zza(1, "gads:drx_debug:in_app_preview_status_url", "https://www.google.com/dfp/inAppPreview");
    public static final zzde<String> zzbht = zzde.zza(1, "gads:drx_debug:debug_signal_status_url", "https://www.google.com/dfp/debugSignals");
    public static final zzde<String> zzbhu = zzde.zza(1, "gads:drx_debug:send_debug_data_url", "https://www.google.com/dfp/sendDebugData");
    public static final zzde<Integer> zzbhv = zzde.zza(1, "gads:drx_debug:timeout_ms", 5000);
    public static final zzde<Integer> zzbhw = zzde.zza(1, "gad:pixel_dp_comparision_multiplier", 1);
    public static final zzde<Boolean> zzbhx = zzde.zza(1, "gad:interstitial_for_multi_window", Boolean.valueOf(false));

    class AnonymousClass1 implements Callable<Void> {
        final /* synthetic */ Context zzamt;

        AnonymousClass1(Context context) {
            this.zzamt = context;
        }

        public /* synthetic */ Object call() throws Exception {
            return zzdb();
        }

        public Void zzdb() {
            zzu.zzgl().initialize(this.zzamt);
            return null;
        }
    }

    public static void initialize(Context context) {
        zzle.zzb(new AnonymousClass1(context));
    }

    public static List<String> zzkr() {
        return zzu.zzgk().zzkr();
    }

    public static List<String> zzks() {
        return zzu.zzgk().zzks();
    }
}
